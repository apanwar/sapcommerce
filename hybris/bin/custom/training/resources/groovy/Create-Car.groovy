import org.training.model.CarModel;
import org.training.enums.FuelType;

car = modelService.create(CarModel.class);

car.setCode("00001");
car.setName("Honda Amaze");
car.setChasisNumber("GJHGJK36546156");
car.setEngineNumber("NJK654646");
car.setKw(2000);
car.setModel(2021);
car.setFuelType(FuelType.GASOLINE);
Car.setUnit(unitService.getUnitForCode("pieces"));
modelService.save(car);
