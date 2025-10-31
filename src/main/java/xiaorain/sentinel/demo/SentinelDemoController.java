package xiaorain.sentinel.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelDemoController {

//    @Value("${b}")
    private String c;

    @GetMapping("/test")
    @SentinelResource(value="sentinel_test", blockHandler = "handleBlock" )
    public String test(@RequestParam(name = "a") String a) {
        String b = System.getProperties().getProperty("b");
        return a + " | " + b + " | "  + c;



    }

    public static String handleBlock(String a, BlockException ex) {
        return "当前访问人数过多，请稍后再试！" + "入参："+ a + ", 具体信息：" + ex.getMessage();
    }

}
