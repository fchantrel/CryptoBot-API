package fr.fchantrel.cryptobot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fchantrel
 * Classe qui gère la configuration
 *
 */
@Component
public class ResourcesConfiguration {

    /** Host de Elasticsearch. */
    @Value("${elastic.host}")
    private String elasticHost;

    /** Port de Elasticsearch. */
    @Value("${elastic.port}")
    private String elasticPort;

    public String getElasticHost() {
        return elasticHost;
    }

    public void setElasticHost(String elasticHost) {
        this.elasticHost = elasticHost;
    }

    public String getElasticPort() {
        return elasticPort;
    }

    public void setElasticPort(String elasticPort) {
        this.elasticPort = elasticPort;
    }

}
