package com.jm.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCountriesResponse;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	public static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private final CountryRepository countryRepository;

	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	/**
	 * Find a country by name
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findByName(request.getName()));

		return response;
	}

	/**
	 * Find all countries
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesRequest")
	@ResponsePayload
	public GetCountriesResponse getCountries() {
		GetCountriesResponse response = new GetCountriesResponse();
		response.getCountries().addAll(countryRepository.findAll());

		return response;
	}
}
