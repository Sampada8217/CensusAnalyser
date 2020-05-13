package censusanalyser;

public class IndiaCensusDAO {
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;
    public String state;
    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        population=indiaCensusCSV.population;
    }
}
