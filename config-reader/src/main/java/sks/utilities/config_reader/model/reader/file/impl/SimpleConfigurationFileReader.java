package sks.utilities.config_reader.model.reader.file.impl;

import sks.utilities.config_reader.model.reader.IterativeRecordReader;
import sks.utilities.config_reader.model.reader.file.ConfigurationFileReader;

import java.io.*;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class SimpleConfigurationFileReader extends Reader implements ConfigurationFileReader, IterativeRecordReader<String> {
    private final BufferedReader fileBufferedReader;

    public SimpleConfigurationFileReader(final Path filePath) throws FileNotFoundException {
        this.fileBufferedReader = new BufferedReader(new FileReader(filePath.toFile()));
    }

    public SimpleConfigurationFileReader(BufferedReader fileBufferedReader) {
        this.fileBufferedReader = fileBufferedReader;
    }


    @Override
    public Map<String, String> getConfigurationContents() {
        return null;
    }

    @Override
    public String identifySelf() {
        return ConfigurationFileReader.super.identifySelf();
    }

    @Override
    public Iterator<String> iterator() {
        return new FileIterator();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return fileBufferedReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        fileBufferedReader.close();
    }

    public class FileIterator implements Iterator<String> {
        private final Iterator<String> lineIterator = fileBufferedReader.lines().iterator();

        @Override
        public boolean hasNext() {
            return lineIterator.hasNext();
        }

        @Override
        public String next() {
            return lineIterator.next();
        }
    }
}
