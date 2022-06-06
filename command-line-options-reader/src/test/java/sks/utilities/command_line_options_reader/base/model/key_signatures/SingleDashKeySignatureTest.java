package sks.utilities.command_line_options_reader.base.model.key_signatures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sks.utilities.command_line_options_reader.base.KeySignature;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SingleDashKeySignature should")
class SingleDashKeySignatureTest
{
    public KeySignature sut;

    @BeforeEach
    void setUp() {
        sut = new SingleDashKeySignature();
    }

    @Test
    @DisplayName("identify single dashed key correctly")
    void identifySingleDashKeyCorrectly() {
        assertTrue(sut.isKey("-correct"));
    }

    @Test
    @DisplayName("discard key if no key is given after the prefix")
    void discardKeyIfNoKeyIsGivenAfterPrefix() {
        assertFalse(sut.isKey("-"));
    }

    @Test
    @DisplayName("discard keys which don't have correct prefix")
    void discardKeyIfPrefixDoesntMatch() {
        assertFalse(sut.isKey("--some-key"));
    }

    @Test
    @DisplayName("discard keys with no prefix specified")
    void discardKeyIfPrefixIsAbsent() {
        assertFalse(sut.isKey("nothing-here"));
    }

    @Test
    @DisplayName("get key string from possible key")
    void getKeyStringFromPossibleKey() {
        assertEquals("some-key", sut.getKey("-some-key"));
    }
}