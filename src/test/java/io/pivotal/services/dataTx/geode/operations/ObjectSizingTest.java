package io.pivotal.services.dataTx.geode.operations;

import nyla.solutions.core.security.user.data.UserProfile;
import nyla.solutions.core.util.Debugger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectSizingTest
{

    public ObjectSizingTest()
    {
    }

    @BeforeEach
    public void setUp()
    throws Exception
    {
    }

    @Test
    public void testSizeObject()
    {
        UserProfile userProfile = new UserProfile();

        long size = ObjectSizing.sizeObjectBytes(userProfile);

        Debugger.println("size:" + size);
    }


}
