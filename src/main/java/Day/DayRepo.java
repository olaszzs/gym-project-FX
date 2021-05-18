package Day;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Class for creating Day Repository.
 */
public class DayRepo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    /**
     * List of days.
     */
    public List<Day> days;

    /**
     *
     * @throws IOException
     */
    public DayRepo(String path) throws IOException{
        days = OBJECT_MAPPER.readValue(new File(path), new TypeReference<>() {});
    }

    /*public DayRepo(String path) throws IOException {
        loadDays(DayRepo.class.getClassLoader().getResourceAsStream());
    }*/

    private void loadDays(InputStream is) throws IOException {
        days = OBJECT_MAPPER.readValue(is, new TypeReference<>() {
        });
    }

    /**
     *
     * @return the days list.
     */
    public List<Day> getAll() {
        return days;
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args){

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
