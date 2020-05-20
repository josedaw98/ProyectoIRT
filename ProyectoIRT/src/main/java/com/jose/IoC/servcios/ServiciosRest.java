package com.jose.IoC.servcios;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jose.IoC.datos.marca.Marca;


@Service
public class ServiciosRest {
	
	public void JSONPUT(Marca marca) throws URISyntaxException {

        URI myURL = new URI("http://localhost:9090/JSON/add");
        RequestEntity<Marca> request = new RequestEntity<Marca>(marca, HttpMethod.PUT, myURL);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(request, marca.getClass()); 

    }

}
