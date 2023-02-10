package com.exercice.supermarket.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercice.supermarket.controllers.ProductController;
import com.exercice.supermarket.dto.ProductDTO;
import com.exercice.supermarket.dto.TicketDTO;
import com.exercice.supermarket.mappers.ProductMapper;
import com.exercice.supermarket.mappers.TicketMapper;
import com.exercice.supermarket.models.Product;
import com.exercice.supermarket.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

	private final ProductService productService;
	
	private final ProductMapper productMapper;
	
	private final TicketMapper ticketMapper;
	
	@Autowired
	public ProductControllerImpl(ProductService productService,
								 ProductMapper productMapper,
								 TicketMapper ticketMapper) {
		this.productService = productService;
		this.productMapper = productMapper;
		this.ticketMapper = ticketMapper;
	}
	
	@Override
	@PostMapping("/insert")
	public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
		Product product = this.productMapper.asEntity(productDTO);
		return new ResponseEntity<>(this.productMapper.asDTO(this.productService.save(product)), 
							    	HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
		/*
		if (this.productService.findById(id).isPresent()) {
			Product product = this.productService.findById(id).get();
			ProductDTO productDTO = this.productMapper.asDTO(product);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		*/
		Product product = this.productService.findById(id).get();
		ProductDTO productDTO = this.productMapper.asDTO(product);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<ProductDTO>> all() {
		return new ResponseEntity<>(this.productMapper.asDTOList(this.productService.findAll()), HttpStatus.OK);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
		if (this.productService.findById(id).isPresent()) {
			Product product = this.productMapper.asEntity(productDTO);
			this.productService.update(product, id);
			return new ResponseEntity<>(this.productMapper.asDTO(this.productService.findById(id).get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@Override
	@PutMapping("/add_products/{id}")
	public ResponseEntity<TicketDTO> addProductsToTicket(@RequestBody List<ProductDTO> productDTOS, @PathVariable("id") Long id) {
		TicketDTO ticketDTO = this.ticketMapper.asDTO(this.productService.addProductsToTicket(this.productMapper.asEntityList(productDTOS), id));
		return new ResponseEntity<>(ticketDTO, HttpStatus.BAD_REQUEST);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDTO> delete(@PathVariable("id") Long id) {
		if (this.productService.findById(id).isPresent()) {
			ProductDTO productDTO = this.productMapper.asDTO(this.productService.findById(id).get());
			// productDTO.setTicket(null);
			this.update(productDTO, id);
			this.productService.deleteById(id);
			return new ResponseEntity<>(productDTO, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
