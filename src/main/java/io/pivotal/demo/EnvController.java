package io.pivotal.demo;

import io.pivotal.demo.domain.Customer;
import io.pivotal.demo.service.CustomerService;
import io.pivotal.demo.service.CustomerServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EnvController {

	private static final Logger logger = LoggerFactory.getLogger(EnvController.class);
	
	@Autowired
	private CustomerServiceImpl customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) throws Exception {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String serverTime = dateFormat.format(date);
		model.put("serverTime", serverTime);
		
		String port = System.getenv("PORT");
		model.put("port", port);

		String instanceGuid = System.getenv("CF_INSTANCE_GUID");
		model.put("instanceGuid", instanceGuid);
		
		String instanceIndex = System.getenv("CF_INSTANCE_INDEX");
		model.put("instanceIndex", instanceIndex);
		
		String vcapApplication = System.getenv("VCAP_APPLICATION");
		model.put("vcapApplication", vcapApplication);
		
		String vcapServices = System.getenv("VCAP_SERVICES");
		model.put("vcapServices", vcapServices);
		
		logger.info("Current date and time = [{}], port = [{}].", serverTime, port);
		logger.info("VCAP_SERVICES [{}] ", vcapServices);
		logger.info("VCAP_APPLICATION [{}] ", System.getenv("VCAP_APPLICATION"));

		return "index";
	}
	
	@RequestMapping(value = "/environmentVariables", method = RequestMethod.GET)
	public String env(Map<String, Object> model) throws Exception {
		
		model.put("environmentVariables",
				new TreeMap<String,String>(System.getenv()));
		return "environmentVariables";
		
	}

	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		logger.warn("Shutting down application instance!");
		System.exit(-1);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String customers(Map<String, Object> model) {
		List<Customer> customers = this.customerService.getCustomers();
		logger.debug("Found " + customers.size());
		model.put("customers", customers);
		return "customers";
	}
}
