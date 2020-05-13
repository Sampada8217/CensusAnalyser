package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;
import java.util.Comparator;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.data";
    private static final String INDIA_STATE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData";
    private static final String INDIA_CENSUS_TYPE_INCORRECT_PATH="./src/test/resources/IndiaStateCensusData.pdf";
    private static final String INDIA_CENSUS_INCORRECT_DELIMITER_PATH="./src/test/resources/IndiaStateCensusData,csv";
    private static final String INDIA_CENSUS_INCORRECT_HEADER_PATH="./src/test/resources/IndiaStateCensusDataHeader.csv";
    private static final String INDIA_STATE_WRONG_FILE_PATH="./src/main/resources/IndiaStateCensusData.data";
    private static final  String INDIA_STATE_TYPE_INCORRECT_PATH="./src/test/resources/IndiaStateCensusData.pdf";
    private static final String INDIA_STATE_INCORRECT_DELIMITER_FILE_PATH = "./src/test/resources/IndiaStateCensusData,csv";

    @Test

    public void givenIndianCensus_withCSVFile_shouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_whenCSVFileTypeIncorrect_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_TYPE_INCORRECT_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSV_whenDelimiterIncorrect_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_INCORRECT_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndianCensusCSV_whenHeaderIncorrect_shouldThrowException() {
        try{
           CensusAnalyser censusAnalyser=new CensusAnalyser();
           ExpectedException exception=ExpectedException.none();
           exception.expect(CensusAnalyserException.class);
           censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_INCORRECT_HEADER_PATH);
        }catch (CensusAnalyserException e){
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianStateCSV_shouldReturnExactCount() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfStateCode = censusAnalyser.loadIndianStateCode(INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfStateCode);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateData_withWrongFile_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_WRONG_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianStateData_whenCSVFileTypeIncorrect_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_TYPE_INCORRECT_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianStateCSV_whenDelimiterIncorrect_shouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_STATE_INCORRECT_DELIMITER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);

        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnState_shouldReturnSortedResult() throws CensusAnalyserException {

            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
    }
}
