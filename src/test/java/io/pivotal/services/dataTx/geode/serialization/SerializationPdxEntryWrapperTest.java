package io.pivotal.services.dataTx.geode.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.services.dataTx.geode.demo.SimpleObject;
import nyla.solutions.core.security.user.data.UserProfile;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SerializationPdxEntryWrapperTest
{

    @Test
    public void isCustom()
    {
        assertTrue(SerializationPdxEntryWrapper.isCustom(new SimpleObject()));
        assertFalse(SerializationPdxEntryWrapper.isCustom(null));
        assertFalse(SerializationPdxEntryWrapper.isCustom(LocalDateTime.now()));
    }

    @Test
    public void test_setJson_not_allow_json_without_type()
    {
        SerializationPdxEntryWrapper<Serializable> s = new SerializationPdxEntryWrapper<>();

        s.setValueJson("");

        assertThrows(IllegalArgumentException.class, () ->
                                    s.setValueJson("Sdfdsfsf sdfsfdsf"));




        assertThrows(IllegalArgumentException.class, () -> {
            UserProfile expected = new UserProfile();
            expected.setEmail("test");

            s.setValueJson(new ObjectMapper().writeValueAsString(expected));
            fail();
        });

        assertThrows(IllegalArgumentException.class, () -> s.setValueJson("@type"));

        s.setValueJson("{\"@type\":\"java.util.Calendar\"}");

    }
}