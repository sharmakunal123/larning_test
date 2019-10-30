package com.learning.webservices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// lsof -i :3000 lsof -i :3000
@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	// www.aapanel.com
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	@RequestMapping("/products")
	public List<Product> getProducts() {
		return service.getProducts();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addProduct")
	public Product addProduct(@RequestBody Product product) {
		product.setDateTime(getCurrentTimeUsingCalendar());
		return service.addProduct(product);
	}
		
	public String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	
	@RequestMapping(value = "/uploadproduct", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public ResponseEntity<Object> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestParam("extraField") String JsonValue) {
		File convertFile = new File("/Users/sumitchouhan/Downloads/images/" + file.getOriginalFilename());
		try {

			convertFile.createNewFile();
			System.out.println("File Path" + convertFile.getAbsolutePath());
			FileOutputStream fOut = new FileOutputStream(convertFile);
			fOut.write(file.getBytes());
			fOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product addProduct = new Product();
		JSONObject jsonObject = new JSONObject(JsonValue);

		String productName = jsonObject.getString("product");
		int id = jsonObject.getInt("id");

//		addProduct.setProduct(productName);
//		addProduct.setID(id);
//		addProduct.setProductImage(convertFile.getAbsolutePath());
//		service.updateProduct(addProduct);

		return new ResponseEntity<>("File is uploaded Successfully", HttpStatus.OK);
	}

}
