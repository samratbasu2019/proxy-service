package com.org.infy.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.org.infy.proxy.property.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class ProxyStarter {
	public static void main(String[] args) {
		SpringApplication.run(ProxyStarter.class, args);
	}
}
