package bleuauction.bleuauction_be.server.properties;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private List<String> domains;

}
