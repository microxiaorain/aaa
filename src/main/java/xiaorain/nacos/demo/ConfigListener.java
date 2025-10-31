package xiaorain.nacos.demo;


import com.alibaba.cloud.nacos.annotation.NacosConfigListener;
import org.springframework.stereotype.Service;

@Service
public class ConfigListener {

    private String data;

    @NacosConfigListener(dataId = "xiaorain-springboot-cloud-demo.yaml",group = "DEFAULT_GROUP", initNotify = true)
    public void rate(String rateConfig) {
        System.out.println("get data :" + rateConfig);
        this.data = rateConfig;
    }

    public String getData() {
        return data;
    }

}
