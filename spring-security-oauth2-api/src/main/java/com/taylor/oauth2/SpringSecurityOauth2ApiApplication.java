package com.taylor.oauth2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.taylor.oauth2.orm.entities.Book;
import com.taylor.oauth2.services.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringSecurityOauth2ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2ApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(Environment environment) {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String corsOrgins = environment.getProperty("corsOrigin");
				registry
						.addMapping("/api/v1/**")
						.allowedOrigins(corsOrgins.split(","))
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
			}
		};
	}

	@Bean
	public CommandLineRunner demo(BookService service) {
		return (args) -> {
			List<Book> books = service.getAllBooks();
			if (!(books != null && books.size() > 0)) {
				// Add initial data
				log.info("Create initial data for books.");

				service.createBook(new Book("Ballinby Boys", "Abraham Stackhouse",
						(short) 2001, "989-28-3705-007-2"));
				service.createBook(new Book("Nothing But Capers", "Amelia Wangerin, Jr.",
						(short) 2002, "989-28-79-11297-4"));
				service.createBook(new Book("Alanna Saves the Day", "Anastasia Inez",
						(short) 2003, "989-28-79-11297-8"));
				service.createBook(new Book("Post Alley", "Arthur McCrumb",
						(short) 2004, "989-28-79-18127-7"));
				service.createBook(new Book("Thatchwork Cottage", "Arturo Hijuelos",
						(short) 2005, "989-28-79-82197-5"));
				service.createBook(new Book("Zero over Twelve", "Bernard Hopf",
						(short) 2006, "989-28-79-05638-4"));
				service.createBook(new Book("Portmeirion", "Bianca Thompson",
						(short) 2007, "989-28-654-5018-9"));
				service.createBook(new Book("Rystwyth", "Bravig Lewisohn",
						(short) 2008, "989-28-79-69807-2"));
				service.createBook(new Book("The Mallemaroking", "Burton Malamud",
						(short) 2009, "989-28-79-69807-4"));
				service.createBook(new Book("Can I Be Honest?", "Carolyn Segal",
						(short) 2010, "989-28-79-05670-4"));
				service.createBook(new Book("No More Lightning", "Charles Fenimore",
						(short) 2011, "989-28-79-82749-6"));
				service.createBook(new Book("9803 North Millworks Road", "Clifford Wolitzer",
						(short) 2012, "989-28-3705-987-7"));
			}
		};
	}

}
