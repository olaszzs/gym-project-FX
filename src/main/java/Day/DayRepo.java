package Day;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DayRepo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private List<Day> days;

    public DayRepo() throws IOException{
        loadDays(DayRepo.class.getClassLoader().getResourceAsStream("days.json"));
    }

    public void loadDays(InputStream is) throws IOException{
        days = OBJECT_MAPPER.readValue(is, new TypeReference<>() {
        });
    }

    public List<Day> getAll() {
        return days;
    }

    public static void main(String[] args) throws  IOException{
        //final var repo = new DayRepo();
        //final var days = repo.getAll();
    }

    /**
     * @return modified toString() to this class.
     */
    @Override
    public String toString() {
        return "DayRepo{" +
                "days=" + days +
                '}';
    }
}
