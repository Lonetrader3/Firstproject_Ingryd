
import com.Kingsley.UserDemo.UserDemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;




@RestController
public class MessageController {
    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class, args);
    }
    private final String messagesFilePath = "src/main/resources/messages.txt";

    @GetMapping("/get_messages")
    public List<String> getMessages() throws IOException {
        return Files.readAllLines(Paths.get(messagesFilePath));
    }


        @GetMapping("/get_message_count")
        public long getMessageCount() throws IOException {
            return Files.lines(Paths.get(messagesFilePath)).count();
        }



        @PostMapping("/post_message")
        public String postMessage(@RequestBody String message) throws IOException {
            try (FileWriter fileWriter = new FileWriter(messagesFilePath, true)) {
                fileWriter.write(message + "\n");
            }
            return "Message posted successfully";
        }






        // Log activity
//        logActivity("New message created");
//
//        return "Message posted successfully";

    private void logActivity(String activity) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String logFilePath = "src/main/resources/log.txt";
        try (FileWriter fileWriter = new FileWriter(logFilePath, true)) {
            fileWriter.write(formattedDateTime + " - " + activity + "\n");
        }
    }


}


