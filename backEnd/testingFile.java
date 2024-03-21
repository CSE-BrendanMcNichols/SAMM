package backEnd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class testingFile{
    @Test
    public void makeNullCourse(){
        Course course = new Course(null, null,
        null, null,
        null, null,
        null, null, null,null);
    }

}