package sks.utilities.config_reader;

public class Config {
    public static int retry;
    public static String name;

    public static class Inner {
        public static String name;
    }

    public static class Again {
        public static String name;
    }

    public int nonstaticRetry;

    public class NonstaticInner {
        public int retry;
    }

    private void nothing() {}
}
