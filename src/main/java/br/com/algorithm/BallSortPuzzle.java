package br.com.algorithm;

import br.com.plugins.ArrayStack;
import br.com.plugins.Estado;
import java.util.ArrayList;
import br.com.model.Ball;
import java.util.LinkedList;
import java.util.List;

public class BallSortPuzzle implements Estado {
    private ArrayList<ArrayStack> stacks = null;
    private int SIZE = 4;
//    private static int count = 0; 
    
    public BallSortPuzzle(ArrayList<ArrayStack> stacks) {
        this.stacks = stacks;
    }

    public ArrayList<ArrayStack> getStacks() {
        return stacks;
    }
    
    @Override
    public String getDescricao() {
        return "Algoritmo para resolução do game BallSortPuzzle";
    }

    @Override
    public boolean ehMeta() {        
        for (ArrayStack stack : stacks) {
            if (!stackCompleted(stack))
                return false;
        }
        return true;
    }
    
    private boolean stackCompleted(ArrayStack stack) {
        if (stack.size() == 0) {
        } else if (stack.size() != SIZE) {
            return false;
        } else {
            String sessionHex = null;
            Object[] stackArray = stack.getArray();
            for (int i = 0; i < stack.size(); i++) {
                Ball currentBall = (Ball) stackArray[i];
                String hexCode = currentBall.getColor().getHexCode();
                if (!hexCode.equals(sessionHex) && sessionHex != null) {
                    return false;
                }
                sessionHex = hexCode;
            }
        }
        return true;
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<BallSortPuzzle>  sucessores() {
        List<BallSortPuzzle> suc = new LinkedList<>();
        
        for (int i = 0; i < stacks.size(); i++) { 
            ArrayStack stack = stacks.get(i);
            // Valida Stack bola disponível
            if (stack.size() > 0 && !stackCompleted(stack)) {
                Ball ball = (Ball) stack.top();
                String hexColor = ball.getColor().getHexCode();
                // valida jogadas possíveis
                for (int j = 0; j < stacks.size(); j++) { 
                    ArrayStack otherStack = stacks.get(j);
                    if (otherStack != stack) {
                        
                        Ball otherBall = (Ball) otherStack.top();
                        String currentHexCode = (otherBall != null) ? otherBall.getColor().getHexCode() : null;
                        boolean isValidPlay = false;
                        
                        if (otherStack.size() == 0) {
                            isValidPlay = true;
                        } else if (otherStack.size() < 4 && currentHexCode.equals(hexColor)) {
                           isValidPlay = true;
                        }
                        
                        if (isValidPlay) {
                            ArrayList<ArrayStack> stacksClone = new ArrayList<>();
                            for (ArrayStack stackCloneItem : stacks) {
                                ArrayStack clone = (ArrayStack) stackCloneItem.clone();
                                stacksClone.add(clone);
                            }
                            
                            Ball ballTemp = (Ball) stacksClone.get(i).pop();
                            stacksClone.get(j).push(ballTemp);
                            suc.add(new BallSortPuzzle(stacksClone));
                        }
                    }
                }
            }
        }
        
//        count++;
//        System.out.println("count: " + count + " Entrada: " + stacks);
//        for (BallSortPuzzle ballSortPuzzle : suc) {
//            System.out.println("ballSortPuzzle("+ ballSortPuzzle.ehMeta()+"): " + ballSortPuzzle.getStacks());
//        }
//        System.out.println("");
        return suc;
    }

    @Override
    public String toString() {
        return stacks.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
