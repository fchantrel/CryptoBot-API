package fr.fchantrel.cryptobot.dao.impl;

import fr.fchantrel.cryptobot.dao.binance.DAOCotationWriter;
import fr.fchantrel.cryptobot.domain.Cotation;
import fr.fchantrel.cryptobot.domain.CotationWithStats;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * @author fchantrel
 */
@Repository
public class DAOCotationWriterImpl implements DAOCotationWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAOCotationWriterImpl.class);

    @Value("${elastic.host}")
    private String elasticHost;

    @Value("${elastic.port}")
    private int elasticPort;

    private RestHighLevelClient client;

    @PostConstruct
    public void initialise() throws IOException {

        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(elasticHost, elasticPort, "http")));
    }

    public void enregistrerCotations(List<Cotation> pLstCotation) {

        try {
            Date currentTime = Calendar.getInstance().getTime();
            for (int i = 0; i < pLstCotation.size(); i++) {
                Cotation currentCotation = pLstCotation.get(i);
                XContentBuilder builder = XContentFactory.jsonBuilder();
                builder.startObject();
                {
                    builder.field("symbol", currentCotation.getSymbol());
                    builder.field("valeur", currentCotation.getValeur().doubleValue());
                    builder.timeField("date", currentCotation.getDate());
                    builder.timeField("dateInsertion", currentTime);
                }
                builder.endObject();
                IndexRequest indexRequest = new IndexRequest("test", "doc")
                        .source(builder);
                /*
                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put("monnaie", currentCotation.getSymbol());
                jsonMap.put("valeur", currentCotation.getValeur().doubleValue());
                jsonMap.put("date", new Date());
                jsonMap.put("dateInsertion", currentTime);

                IndexRequest indexRequest = new IndexRequest("test", "doc")
                        .source(jsonMap);
                */
                indexRequest.timeout("1s");
                // reponse synchrone (exemple asynchrone : https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.4/java-rest-high-document-index.html)
                IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

                LOGGER.debug(indexResponse.toString());
            }


        } catch (Exception e){
            LOGGER.warn("Impossible d'enregistrer les cotations", e);
        }

    }

    public void enregistrerCotationsDetaillees(List<CotationWithStats> pLstCotation) {

        try {
            Date currentTime = Calendar.getInstance().getTime();
            for (int i = 0; i < pLstCotation.size(); i++) {
                CotationWithStats currentCotation = pLstCotation.get(i);
                XContentBuilder builder = XContentFactory.jsonBuilder();
                builder.startObject();
                {
                    builder.field("symbol", currentCotation.getSymbol());
                    builder.field("valeur", currentCotation.getValeur().doubleValue());
                    builder.field("valeurOuverture", currentCotation.getValeurOuverture().doubleValue());
                    builder.field("volume", currentCotation.getVolume());
                    builder.field("evolution", currentCotation.getPourcentageEvolutionPrix().doubleValue());

                    builder.timeField("date", currentCotation.getDate());
                    builder.timeField("dateInsertion", currentTime);
                }
                builder.endObject();
                IndexRequest indexRequest = new IndexRequest("testdetail", "doc")
                        .source(builder);

                indexRequest.timeout("1s");
                // reponse synchrone (exemple asynchrone : https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.4/java-rest-high-document-index.html)
                IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

                LOGGER.debug(indexResponse.toString());
            }


        } catch (Exception e){
            LOGGER.warn("Impossible d'enregistrer les cotations", e);
        }

    }
}
