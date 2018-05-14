package com.it.ittest.jfx.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;

/**
 * Jersey REST client generated for REST resource:EmployeeResource [employee]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author andrey
 */
public class NewJerseyClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ittest-web/api";

    public NewJerseyClient(String path) {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(clientConfig);
        webResource = client.resource(BASE_URI).path(path);
    }

    public void remove(String id) throws UniformInterfaceException {
        webResource.path(MessageFormat.format("{0}", id)).delete();
    }

    public String countREST() throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("count");
        return resource.accept(MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T findAll_XML(GenericType<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findAll_JSON(GenericType<T> responseType) throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public void edit_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_XML).put(requestEntity);
    }

    public void edit_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_JSON).put(requestEntity);
    }

    public <T> T buscarMientras(GenericType<T> responseType, String campo, String valor) throws UniformInterfaceException {
        return webResource.path(MessageFormat.format("{0}/{1}", campo, valor)).post(responseType);
    }

    public void create_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_XML).post(requestEntity);
    }

    public void create_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_JSON).post(requestEntity);
    }

    public <T> T findRange_XML(GenericType<T> responseType, String from, String to) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}/{1}", from, to));
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findRange_JSON(GenericType<T> responseType, String from, String to) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}/{1}", from, to));
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T find_XML(GenericType<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}", id));
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T find_JSON(GenericType<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}", id));
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }

}