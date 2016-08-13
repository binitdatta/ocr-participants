package com.rollingstone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class OcrParticipantsApplication implements CommandLineRunner {
	
	private static List<Participant> participants = new ArrayList<Participant>();

    public static void main(String[] args) {
        SpringApplication.run(OcrParticipantsApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		participants.add(new Participant("Ryan", "Baxter", "MA", "S", Arrays.asList("123", "456")));
		participants.add(new Participant("Stephanie", "Baxter", "MA", "S", Arrays.asList("456")));		
	}
	
	@RequestMapping("/")
	public List<Participant> getParticipants() {
		return participants;
	}
	
	@RequestMapping("/races/{id}")
	public List<Participant> getParticipants(@PathVariable String id) {
		return participants.stream().filter(p -> p.getRaces().contains(id)).collect(Collectors.toList());
	}
}

