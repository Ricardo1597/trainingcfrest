package com.accenture.trainingcf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.trainingcf.dto.ClientsTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ClientsControllerTest {
	@Autowired
	private ClientsController controller;

	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static ClientsTO client;

	private static void getClientTest() {
		ClientsTO clientsTO = new ClientsTO();
		clientsTO.setName("Client Name");
		clientsTO.setAge(35);
		clientsTO.setFamilyName("Client Family Name");
		client = clientsTO;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getClientTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void aa_saveClient() throws UnsupportedEncodingException, Exception {

		final byte[] clientAsByteArray = mapper.writeValueAsBytes(client);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/Client")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(clientAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ClientsTO objResult = mapper.readValue(result, ClientsTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		client.setId(objResult.getId());
	}

	@Test
	public void ab_changeClient() throws UnsupportedEncodingException, Exception {

		String newName = "Produto Teste Alterado";
		client.setName(newName);
		final byte[] clientAsByteArray = mapper.writeValueAsBytes(client);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/Client/"+client.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(clientAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ClientsTO objResult = mapper.readValue(result, ClientsTO.class);

		assertThat(objResult.getId()).isEqualTo(client.getId());
		assertThat(objResult.getName()).isEqualTo(newName);

	}

	@Test
	public void ac_getAllClients() throws UnsupportedEncodingException, Exception {

		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Client")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<ClientsTO> objResult = Arrays.asList(mapper.readValue(result, ClientsTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);

	}
	
	@Test
	public void ad_getOneClient() throws UnsupportedEncodingException, Exception {

		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/Client/"+client.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final ClientsTO objResult = mapper.readValue(result, ClientsTO.class);
		assertThat(objResult.getId()).isEqualTo(client.getId());

	}
	@Test
	public void az_deleteClient() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/Client/"+client.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

	}

}
