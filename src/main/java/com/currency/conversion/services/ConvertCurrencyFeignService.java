package com.currency.conversion.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.conversion.bean.CurrencyConversionBean;

@Service
public class ConvertCurrencyFeignService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	public CurrencyConversionBean convertCurrencyFeign(String from,String to,BigDecimal quantity) {
		
		CurrencyConversionBean currencyConversionBean=currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
		
		logger.info("{}",currencyConversionBean);
		
		return new CurrencyConversionBean(currencyConversionBean.getId(),
				currencyConversionBean.getFrom(), currencyConversionBean.getTo(),
				currencyConversionBean.getConversionMultiple(), quantity, quantity.multiply(currencyConversionBean.getConversionMultiple()),
				currencyConversionBean.getPort());
	}
}
