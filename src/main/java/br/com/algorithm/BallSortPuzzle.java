package br.com.algorithm;

import br.com.plugins.ArrayStack;
import br.com.plugins.Estado;
import java.util.ArrayList;
import br.com.model.Ball;
import br.com.util.Config;
import java.util.LinkedList;
import java.util.List;

public class BallSortPuzzle implements Estado {
    private ArrayList<ArrayStack> stacks = null;
    private int SIZE = Config.STACK_CAP;
    
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
            return isHomogeneous(stack);
        }
        return true;
    }

    private boolean isHomogeneous(ArrayStack stack) {
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
        return true;
    }
    
    private boolean stackAvailable(ArrayStack stack) {
        if (stackCompleted(stack)) {
            return false;
        } else if (stack.size() == 0) {
            return false;
        } else {
            boolean homogeneous = isHomogeneous(stack);
            if (homogeneous && stack.size() >= SIZE-1) {
                return false;
            }
            return true;
        }
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<BallSortPuzzle> sucessores() {
        List<BallSortPuzzle> suc = new LinkedList<>();
        
        for (int i = 0; i < stacks.size(); i++) { 
            ArrayStack stack = stacks.get(i);
            // Valida tubo para jogada
            if (stackAvailable(stack)) {
                Ball ball = (Ball) stack.top();
                String hexColor = ball.getColor().getHexCode();
                
                // Verifica tubos disponíveis
                for (int j = 0; j < stacks.size(); j++) { 
                    ArrayStack otherStack = stacks.get(j);
                    if (otherStack != stack) {
                        Ball otherBall = (Ball) otherStack.top();
                        String currentHexCode = (otherBall != null) ? otherBall.getColor().getHexCode() : null;
                        boolean isValidPlay = false;

                        if (stack.size() == 1 && otherStack.size() == 0) {// Não gera uma nova possibilidade
                            isValidPlay = false;
                        } else if (otherStack.size() == 0) {
                            isValidPlay = true;
                        } else if (otherStack.size() < SIZE && currentHexCode.equals(hexColor)) {
                           isValidPlay = true;
                        }

                        if (isValidPlay) { 
                            ArrayList<ArrayStack> stacksClone = new ArrayList<>();
                            for (ArrayStack stackCloneItem : stacks) {
                                ArrayStack clone = (ArrayStack) stackCloneItem.clone();
                                stacksClone.add(clone);
                            }

                            stacksClone.get(j).push(stacksClone.get(i).pop());
                            BallSortPuzzle newSortPuzzle = new BallSortPuzzle(stacksClone);
                            suc.add(newSortPuzzle);
                        }
                    }
                }
            }
        }
        
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

    @Override
    public boolean equals(Object obj) {
        BallSortPuzzle other = (BallSortPuzzle) obj;
        return other.toString().equals(this.toString());
    }
    
    
}
