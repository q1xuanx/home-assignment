package com.example.homework_assignment.components;


import com.example.homework_assignment.models.Details;
import com.example.homework_assignment.models.Hotel;
import com.example.homework_assignment.response.AcmeResponse;
import com.example.homework_assignment.response.PatagoniaResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;


@Component
public class FetchData {
    public static List<Hotel> listHotel;
    private final WebClient webClient;
    public FetchData(WebClient.Builder clientBuilder) {
        String BASE_URL = "https://5f2be0b4ffc88500167b85a0.mockapi.io/suppliers";
        this.webClient = clientBuilder.baseUrl(BASE_URL).build();
    }
    public void getListHotel() {
        listHotel = webClient.get().uri("/paperflies").retrieve().bodyToMono(new ParameterizedTypeReference<List<Hotel>>() {}).block();
        List<AcmeResponse> acme = fetchAcme();
        List<PatagoniaResponse> patagonia = fetchPatagonia();
        for (Hotel hotel : listHotel) {
            Optional<AcmeResponse> getAcme = acme.stream().filter(s -> s.getId().equals(hotel.getHotel_id())).findFirst();
            Optional<PatagoniaResponse> getPatagonia = patagonia.stream().filter(s -> s.getId().equals(hotel.getHotel_id())).findFirst();
            if(getAcme.isPresent()) {
                AcmeResponse des = getAcme.get();
                hotel.getLocation().setCity(des.getCity());
                if(des.getFacilities() != null) {
                    for(String s : des.getFacilities()){
                        boolean find = hotel.getAmenities().getGeneral().stream().anyMatch(am -> am.toLowerCase().replace(" ", "").equals(s.toLowerCase().replace(" ", "")));
                        if(!find) {
                            hotel.getAmenities().getGeneral().add(s.toLowerCase().trim());
                        }
                    }
                }
            }
            if(getPatagonia.isPresent()){
                PatagoniaResponse images = getPatagonia.get();
                hotel.getLocation().setLat(images.getLat());
                hotel.getLocation().setLng(images.getLng());
                if (images.getImages().getRooms() != null){
                    for (Details details : images.getImages().getRooms()){
                        boolean checkIsContain = hotel.getImages().getRooms().stream().anyMatch(s -> s.getLink().trim().equals(details.getLink().trim()));
                        if (!checkIsContain) {
                            hotel.getImages().getRooms().add(details);
                        }
                    }
                }
                hotel.getImages().setAmenities(images.getImages().getAmenities());
                if(images.getAmenities() != null) {
                    for (String s : images.getAmenities()) {
                        boolean find = hotel.getAmenities().getRoom().stream().anyMatch(pet -> pet.toLowerCase().replace(" ", "").equals(s.toLowerCase().replace(" ", "")));
                        if (!find) {
                            hotel.getAmenities().getRoom().add(s.toLowerCase().trim());
                        }
                    }
                }
            }
        }
    }
    private List<AcmeResponse> fetchAcme(){
        return webClient.get().uri("/acme").retrieve().bodyToMono(new ParameterizedTypeReference<List<AcmeResponse>>() {}).block();
    }
    private List<PatagoniaResponse> fetchPatagonia(){
        return webClient.get().uri("/patagonia").retrieve().bodyToMono(new ParameterizedTypeReference<List<PatagoniaResponse>>() {}).block();
    }
    @PostConstruct
    public void hello(){
        getListHotel();
        System.out.println("Fetch data completed");
    }
}
