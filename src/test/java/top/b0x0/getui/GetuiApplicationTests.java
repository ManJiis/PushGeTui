package top.b0x0.getui;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@SpringBootTest
class GetuiApplicationTests {

    @Test
    void contextLoads() {

        TreeMap<String, Object> map = new TreeMap<String, Object>();
        map.put("11", 11);
        map.put("22", 22);
        map.put("33", 33);

        for (int i = 0; i < 10; i++) {
            for (String s : map.keySet()) {
                System.out.println("s = " + s);
            }
            System.out.println("-----------------------");
        }

    }

    @Test
    void contextLoads2() {
        TreeSet<String> map = new TreeSet<String>();
        map.add("11");
        map.add("22");
        map.add("33");
        map.add("22");


        for (int i = 0; i < 10; i++) {
            for (String s : map) {
                System.out.println("s = " + s);
            }
            System.out.println("-----------------------");
        }

    }

    @Test
    void contextLoads3() {
        Set<String> map = new HashSet<String>();
        map.add("11");
        map.add("22");
        map.add("33");


        for (int i = 0; i < 10; i++) {
            for (String s : map) {
                System.out.println("s = " + s);
            }
            System.out.println("-----------------------");
        }

    }

}
