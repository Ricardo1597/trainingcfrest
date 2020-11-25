package com.accenture.trainingcf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.accenture.trainingcf.dto.ProductsTO;
import com.accenture.trainingcf.dto.SalesOrderItemTO;
import com.accenture.trainingcf.dto.SalesOrderTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles(profiles = { "test" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesOrderTest {
	@Autowired
	private SalesOrderController controller;

	// Mock testing variables
	private static MockMvc mockMvc;
	private static ObjectMapper mapper;
	private static SalesOrderTO salesOrder;

	private static void getSalesOrderTest() {
		List<SalesOrderItemTO> listOfItems = new ArrayList<SalesOrderItemTO>();
		ProductsTO product1 = new ProductsTO();
		product1.setName("Product Test");
		product1.setManufacturer("MAnufacturerTest");
		product1.setBasePrice(20.58);
		product1.setSalesPrice(20.58);
		product1.setQuantity(3);
		
		SalesOrderItemTO item1 = new SalesOrderItemTO();
		item1.setStatus("D");
//		item1.setProduct(product1);
		listOfItems.add(item1);
		
		SalesOrderTO salesOrdersTO = new SalesOrderTO();
		salesOrdersTO.setStatus("D");
		salesOrdersTO.setItems(listOfItems);
		salesOrder = salesOrdersTO;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		getSalesOrderTest();
	}

	@Before
	public void setUpBefore() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void aa_saveSalesOrder() throws UnsupportedEncodingException, Exception {

		final byte[] salesOrderAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(salesOrderAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);

		assertThat(objResult.getId()).isNotEmpty();
		salesOrder.setId(objResult.getId());
//		salesOrder.setCreatedAt(objResult.getCreatedAt());
//		salesOrder.setCreatedBy(objResult.getCreatedBy());
//		salesOrder.setModifiedAt(objResult.getModifiedAt());
//		salesOrder.setModifiedBy(objResult.getModifiedBy());
//		salesOrder.setItems(objResult.getItems());
		
	}

	@Test
	public void ab_changeSalesOrder() throws UnsupportedEncodingException, Exception {

		String newStatus = "O";
		salesOrder.setStatus(newStatus);
		final byte[] salesOrderAsByteArray = mapper.writeValueAsBytes(salesOrder);

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(salesOrderAsByteArray);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);

		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());
		assertThat(objResult.getStatus()).isEqualTo(newStatus);

	}

	@Test
	public void ac_getAllSalesOrder() throws UnsupportedEncodingException, Exception {

		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder")
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final List<SalesOrderTO> objResult = Arrays.asList(mapper.readValue(result, SalesOrderTO[].class));
		assertThat(objResult.size()).isGreaterThan(0);

	}
	
	@Test
	public void ad_getOneSalesOrder() throws UnsupportedEncodingException, Exception {

		
		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();

		final SalesOrderTO objResult = mapper.readValue(result, SalesOrderTO.class);
		assertThat(objResult.getId()).isEqualTo(salesOrder.getId());

//		assertThat(objResult.getItems().size()).isGreaterThan(0);
//		assertThat(objResult.getItems().get(0).getProduct().getQuantity()).isEqualTo(123);
	}
	
	@Test
	public void az_deleteSalesOrder() throws UnsupportedEncodingException, Exception {

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/SalesOrder/"+salesOrder.getId())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value())).andReturn().getResponse()
				.getContentAsString();

	}

}
