package com.oracle.service;


import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

@Service("dnsService")
public class DnsService {

	public int checkDnsStatus(String url) throws TextParseException {
			Lookup l = new Lookup(url, Type.A);
			l.run();
			return l.getResult() ;

	}

}
