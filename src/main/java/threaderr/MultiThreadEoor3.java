package threaderr;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MultiThreadEoor3
 * @Author chenchen
 * @Date 2020/3/10 21:09
 * @Version 1.0
 * 发布逸出
 **/
public class MultiThreadEoor3 {
    private Map<String,String> states;

    public MultiThreadEoor3() {
        this.states = new HashMap<>();
        states.put("1","周2");
        states.put("2","周3");
        states.put("3","周4");
        states.put("4","周5");
    }
    public Map<String,String> getStates(){
        return states;
    }

    public static void main(String[] args) {
        MultiThreadEoor3 multiThreadEoor3 = new MultiThreadEoor3();
        Map<String, String> states = multiThreadEoor3.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
