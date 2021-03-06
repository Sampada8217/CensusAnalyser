package censusanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

public class CensusAnalyser {
    List<CensusDAO> censusDAOS = null;

    public CensusAnalyser() {

    }

    public enum Country{India,US}
    private Country country;
    Map<String,CensusDAO> censusCSVMap=null;

    public CensusAnalyser(Country country) {
        this.country=country;
    }

    public int loadCensusData(Country country,String... csvFilePath) throws CensusAnalyserException {
        censusCSVMap = CensusAdapterFactory.getCensusData(country,csvFilePath);
        return censusCSVMap.size();
    }


    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEntries;
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        ArrayList censusDTOS=censusCSVMap.values().stream().
                sorted(censusCSVComparator).
                map(censusDAO-> censusDAO.getCensusDTO(country)).
                collect(toCollection(ArrayList::new));
        String sortedStateCensus = new Gson().toJson(censusDTOS);
        return sortedStateCensus;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVMap== null || censusCSVMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.population);
        ArrayList censusDTOS=censusCSVMap.values().stream().
                sorted(censusCSVComparator).
                map(censusDAO-> censusDAO.getCensusDTO(country)).
                collect(toCollection(ArrayList::new));
        String sortedPopulationCensus = new Gson().toJson(censusDTOS);
        return sortedPopulationCensus;
    }

    public String getPopulationDensityWiseSortedData() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.populationDensity);
        ArrayList censusDTOS=censusCSVMap.values().stream().
                sorted(censusCSVComparator).
                map(censusDAO-> censusDAO.getCensusDTO(country)).
                collect(toCollection(ArrayList::new));
        String sortedDensityCensus = new Gson().toJson(censusDTOS);
        return sortedDensityCensus;
    }
    public String getPopulationAreaWiseSortedData() throws CensusAnalyserException {
        if (censusCSVMap == null || censusCSVMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.totalArea);
        ArrayList censusDTOS=censusCSVMap.values().stream().
                sorted(censusCSVComparator).
                map(censusDAO-> censusDAO.getCensusDTO(country)).
                collect(toCollection(ArrayList::new));
        String sortedDensityCensus = new Gson().toJson(censusDTOS);
        return sortedDensityCensus;
    }
}
