package io.pivotal.services.dataTx.geode.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExportFileTypeTest
{

    @Test
    public void test_general()
    {
        assertEquals("gfd",ExportFileType.gfd.toString());
        assertEquals("json",ExportFileType.json.toString());
        assertEquals("csv",ExportFileType.csv.toString());
    }

}