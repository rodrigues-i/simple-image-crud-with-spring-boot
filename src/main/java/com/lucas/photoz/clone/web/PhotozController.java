package com.lucas.photoz.clone.web;

import java.io.IOException;

import com.lucas.photoz.clone.service.PhotozService;
import com.lucas.photoz.clone.model.Photo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PhotozController {

	private final PhotozService photozService;

	public PhotozController(PhotozService photozService) {
		this.photozService = photozService;
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/photoz")
	public Iterable<Photo> get() {
		return photozService.get();
	}

	@GetMapping("/photoz/{id}")
	public Photo get(@PathVariable Integer id) {
		Photo photo = photozService.get(id);
		if (photo == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return photo;
	}

	@DeleteMapping("/photoz/{id}")
	public void delete(@PathVariable Integer id) {
		photozService.remove(id);
	}

	@PostMapping("/photoz")
	public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
		
		return photozService.save(file.getOriginalFilename(), file.getContentType() ,file.getBytes());
	}
}
