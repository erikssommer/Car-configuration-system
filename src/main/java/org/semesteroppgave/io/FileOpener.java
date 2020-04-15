package org.semesteroppgave.io;

import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {
    void open(Path filePath) throws IOException;
}
