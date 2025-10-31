package xiaorain.nacos.demo;


import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@NacosConfig(dataId = "nacos.bean.config.test", group = "DEFAULT_GROUP")
@Component
public class UserConfig {

    private String key;

    private boolean refresh;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

}
