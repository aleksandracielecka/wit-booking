package pl.sdacademy.booking.service;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.booking.data.EventEntity;
import pl.sdacademy.booking.data.ItemEntity;
import pl.sdacademy.booking.model.EventDto;
import pl.sdacademy.booking.model.NewEventDto;
import pl.sdacademy.booking.repository.EventRepository;
import pl.sdacademy.booking.repository.ItemRepository;
import pl.sdacademy.booking.util.TimeNowStub;
import pl.sdacademy.booking.validator.NewEventDtoValidator;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class EventService {

    private final ItemRepository itemRepository;

    private final EventRepository eventRepository;


    public EventService(ItemRepository itemRepository, EventRepository eventRepository) {
        this.itemRepository = itemRepository;
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findEvents() {
        log.info("findEvents");
        List<EventDto> result = new ArrayList<>();
        List<EventEntity> eventEntities = eventRepository.findEvents();

        for (EventEntity entity : eventEntities) {
            result.add(EventDto.builder()
                    .id(entity.getId())
                    .itemName(entity.getItem().getName())
                    .itemPrice(entity.getItem().getPrice())
                    .fromTime(entity.getFrom())
                    .toTime(entity.getTo())
                    .build());
        }
        return result;
    }

    public String addEvent(NewEventDto newEvent) {
     //   Long eventsByName = eventRepository.findEventsByDate(newEvent.getFromTime());
     //   if (eventsByName != null) {
     //       return "Sesja już istnieje.";
     //   }
        List<String> validate = NewEventDtoValidator.validate(newEvent, new TimeNowStub());
        if (validate.size() !=0){
            String message =  String.join(", ", validate);
            return message;
        }
        EventEntity eventEntity = new EventEntity();
        Long itemByName = itemRepository.findItemByName(newEvent.getItemName());//szukamy primary key
        if(itemByName == null || itemByName == -1L){
            return "Nie znaleziono takich obiektów";

        }
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemByName);

        //tutaj bedzie wyszukiwanie id_itemu po jego nazwie - być może można wykorzystać metode repostitory Item findbyName
        eventEntity.setItem(itemEntity);        //przekazujemy primary key
        eventEntity.setFrom(newEvent.getFromTime());
        eventEntity.setTo(newEvent.getToTime());
        eventRepository.addEvent(eventEntity);
        return "Sesja została zapisana";
    }
    }


