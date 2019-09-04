package com.oracle.dns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.xbill.DNS.TextParseException;

import com.oracle.controllers.DnsController;
import com.oracle.service.DnsService;

@SpringBootTest
@AutoConfigureMockMvc
public class DnsControllerTest {

	@Autowired
	private DnsController dnsController;

	@MockBean
	private DnsService dnsService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Autowired
	private MockMvc mvc;

	@Test
	public void controllerTest() throws Exception {
		this.mvc.perform(get("/api/v1/userp2020.oracle.com")).andExpect(status().isOk());
	}

	@Test
	public void testDnsControllerOkStatus() throws TextParseException {

		when(dnsService.checkDnsStatus("userp2020.oracle.com")).thenReturn(0);

		ResponseEntity user = dnsController.dnsStatus("userp2020.oracle.com");

		verify(dnsService).checkDnsStatus("userp2020.oracle.com");

		assertEquals(200, user.getStatusCodeValue());
	}

	@Test
	public void testDnsControllerFailStatus() throws TextParseException {

		when(dnsService.checkDnsStatus("userp2020.oracle.com")).thenReturn(1);

		ResponseEntity user = dnsController.dnsStatus("userp2020.oracle.com");

		verify(dnsService).checkDnsStatus("userp2020.oracle.com");

		assertEquals(404, user.getStatusCodeValue());
	}

}
