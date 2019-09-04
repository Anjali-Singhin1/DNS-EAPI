package com.oracle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.TextParseException;

import com.oracle.service.DnsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "DNS STATUS", description = "CHECK DNS STATUS API SERVICE")
public class DnsController {

	@Autowired
	DnsService dnsService;

	@ApiOperation(value = "dnsStatus")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No results found"),
			@ApiResponse(code = 500, message = "Internal Server Error.") })
	@RequestMapping("/{url}")
	public ResponseEntity dnsStatus(@PathVariable String url) {
		int value;
		try {
			value = dnsService.checkDnsStatus(url);
		} catch (TextParseException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity(value == Lookup.SUCCESSFUL ? HttpStatus.OK : HttpStatus.NOT_FOUND,
				value == Lookup.SUCCESSFUL ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}

}