package xiaorain.nacos.demo;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosDemoController {

    @NacosConfig(dataId = "bean.property.nacos.config.test", group = "DEFAULT_GROUP", key = "user.config.demo")
    private String demo;

    @Autowired
    private ConfigListener configListener;

    @Autowired
    private UserConfig userConfig;

    @GetMapping("/config")
    public String getConfig() {
       return configListener.getData();
    }

    @GetMapping("/config2")
    public String getConfig2() {
        return JSONObject.toJSONString(this.userConfig);
    }

    @GetMapping("/config3")
    public String getConfig3() {
        return this.demo;
    }

}
