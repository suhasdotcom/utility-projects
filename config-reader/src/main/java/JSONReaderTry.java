import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class JSONReaderTry {
    public static void main(String[] args) {
        try {
            Configurations configs = new Configurations();
            FileBasedConfigurationBuilder<JSONConfiguration> builder =
                    configs.fileBasedBuilder(JSONConfiguration.class, "D:\\Projects\\IdeaProjects\\utility-projects\\config-reader\\src\\main\\resources\\app-properties.json");

            FileBasedConfiguration config = builder.getConfiguration();

            // Access the nested key using dot notation
            String nestedValue = config.getString("some.nested.key");
            int fuck = config.getInt("some.nested.key.key1");
            System.out.println("Nested value: " + nestedValue);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
