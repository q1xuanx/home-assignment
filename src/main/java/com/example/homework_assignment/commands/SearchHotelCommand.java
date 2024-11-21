package com.example.homework_assignment.commands;

import com.example.homework_assignment.components.FetchData;
import com.example.homework_assignment.models.Hotel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.*;
import java.util.stream.Collectors;

@ShellComponent
public class SearchHotelCommand {
    @ShellMethod(key = "my_hotel_merger", value = "Input hotel_id or destination_id for search")
    public String search (@ShellOption(defaultValue = "none") String hotel_id, @ShellOption(defaultValue = "none") String destination_id) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        List<Hotel> temp = new ArrayList<>();
        List<Hotel> listHotel = new ArrayList<>(FetchData.listHotel);
        hotel_id = hotel_id.trim();
        destination_id = destination_id.trim();
        if (hotel_id.equals("none") && destination_id.equals("none")){
            return obj.writeValueAsString(listHotel);
        }else if (hotel_id.equals("none")){
            Set<String> searchDestination = Arrays.stream(destination_id.split(",")).collect(Collectors.toSet());
            temp = searchHotelByDestination(listHotel, searchDestination);
            return obj.writeValueAsString(temp);
        }else if (destination_id.equals("none")){
            Set<String> searchHotelId = Arrays.stream(hotel_id.split(",")).collect(Collectors.toSet());
            temp = searchHotelById(listHotel,searchHotelId);
            return obj.writeValueAsString(temp);
        }
        List<String> listHotelId = Arrays.stream(hotel_id.split(",")).toList();
        List<String> listDestinationId = Arrays.stream(destination_id.split(",")).toList();
        int i = 0, j = 0;
        while(i < listHotelId.size() || j < listDestinationId.size()){
            String hotelId;
            String destinationId;
            if (i <= listHotelId.size() - 1){
                hotelId = listHotelId.get(i);
                i++;
            }else {
                hotelId = "none";
            }
            if (j <= listDestinationId.size()-1){
                destinationId = listDestinationId.get(j);
                j++;
            }else {
                destinationId = "none";
            }
            Optional<Hotel> isContains = listHotel.stream().filter(hotel -> hotel.getHotel_id().equals(hotelId) && hotel.getDestination_id().equals(destinationId)).findFirst();
            if(isContains.isPresent()){
                boolean isMatch = temp.stream().anyMatch(hotel -> hotel.getHotel_id().equals(isContains.get().getHotel_id()));
                if (!isMatch){
                    Hotel hotel = isContains.get();
                    temp.add(hotel);
                }
            }
        }
        return obj.writeValueAsString(temp);
    }
    public List<Hotel> searchHotelByDestination(List<Hotel> listHotel,Set<String> listDestinationId) throws JsonProcessingException {
        List<Hotel> temp = new ArrayList<>();
        for (String s : listDestinationId){
            List<Hotel> isContains = listHotel.stream().filter(hotel -> hotel.getDestination_id().equals(s)).collect(Collectors.toList());
            for(Hotel hotel : isContains){
                if(!temp.contains(hotel)){
                    temp.add(hotel);
                }
            }
        }
        return temp;
    }
    public List<Hotel> searchHotelById(List<Hotel> listHotel,Set<String> searchHotelId) throws JsonProcessingException {
        List<Hotel> temp = new ArrayList<>();
        for (String s : searchHotelId){
            Optional<Hotel> isContains = listHotel.stream().filter(hotel -> hotel.getHotel_id().equals(s)).findFirst();
            isContains.ifPresent(temp::add);
        }
        return temp;
    }
}
