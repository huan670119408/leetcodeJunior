package design;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 */
public class MinStack {

    private Stack<Integer> data ;

    private Stack<Integer> helper;

    public MinStack() {
        data = new Stack();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(helper.isEmpty() || helper.peek()>x ){
            helper.push(x);
        } else {
            helper.push(helper.peek());
        }
    }

    public void pop() {
        data.pop();
        helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return helper.peek();
    }

}
