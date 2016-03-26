package org.jurassicraft.server.entity.base;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.server.api.IHybrid;
import org.jurassicraft.server.configuration.JCConfigurations;
import org.jurassicraft.server.dinosaur.*;
import org.jurassicraft.server.entity.item.*;
import org.jurassicraft.server.period.EnumTimePeriod;
import org.jurassicraft.server.vehicles.helicopter.HelicopterBaseEntity;
import org.jurassicraft.server.vehicles.helicopter.modules.HelicopterSeatEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JCEntityRegistry
{
    private static List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>();
    private static HashMap<EnumTimePeriod, List<Dinosaur>> dinosaursFromPeriod = new HashMap<EnumTimePeriod, List<Dinosaur>>();

    public static final Dinosaur dodo = new DodoDinosaur();
    public static final Dinosaur achillobator = new AchillobatorDinosaur();
    public static final Dinosaur anklyosaurus = new AnkylosaurusDinosaur();
    public static final Dinosaur brachiosaurus = new BrachiosaurusDinosaur();
    public static final Dinosaur carnotaurus = new CarnotaurusDinosaur();
    public static final Dinosaur coelacanth = new CoelacanthDinosaur();
    public static final Dinosaur compsognathus = new CompsognathusDinosaur();
    public static final Dinosaur dilophosaurus = new DilophosaurusDinosaur();
    public static final Dinosaur dunkleosteus = new DunkleosteusDinosaur();
    public static final Dinosaur gallimimus = new GallimimusDinosaur();
    public static final Dinosaur giganotosaurus = new GiganotosaurusDinosaur();
    public static final Dinosaur hypsilophodon = new HypsilophodonDinosaur();
    public static final Dinosaur indominus = new IndominusDinosaur();
    public static final Dinosaur majungasaurus = new MajungasaurusDinosaur();
    public static final Dinosaur parasaurolophus = new ParasaurolophusDinosaur();
    public static final Dinosaur pteranodon = new PteranodonDinosaur();
    public static final Dinosaur rugops = new RugopsDinosaur();
    public static final Dinosaur segisaurus = new SegisaurusDinosaur();
    public static final Dinosaur spinosaurus = new SpinosaurusDinosaur();
    public static final Dinosaur stegosaurus = new StegosaurusDinosaur();
    public static final Dinosaur triceratops = new TriceratopsDinosaur();
    public static final Dinosaur tyrannosaurus = new TyrannosaurusDinosaur();
    public static final Dinosaur velociraptor = new VelociraptorDinosaur();
    public static final Dinosaur leptictidium = new LeptictidiumDinosaur();
    public static final Dinosaur microceratus = new MicroceratusDinosaur();
    public static final Dinosaur oviraptor = new OviraptorDinosaur();
    public static final Dinosaur apatosaurus = new ApatosaurusDinosaur();
    public static final Dinosaur othnielia = new OthnieliaDinosaur();
    public static final Dinosaur dimorphodon = new DimorphodonDinosaur();
    public static final Dinosaur tylosaurus = new TylosaurusDinosaur();
    public static final Dinosaur ludodactylus = new LudodactylusDinosaur();
    public static final Dinosaur protoceratops = new ProtoceratopsDinosaur();
    public static final Dinosaur tropeognathus = new TropeognathusDinosaur();
    public static final Dinosaur leaellynasaura = new LeaellynasauraDinosaur();
    public static final Dinosaur herrerasaurus = new HerrerasaurusDinosaur();
    public static final Dinosaur velociraptor_blue = new VelociraptorBlueDinosaur();
    public static final Dinosaur velociraptor_delta = new VelociraptorDeltaDinosaur();
    public static final Dinosaur velociraptor_charlie = new VelociraptorCharlieDinosaur();
    public static final Dinosaur velociraptor_echo = new VelociraptorEchoDinosaur();
    public static final Dinosaur therizinosaurus = new TherizinosaurusDinosaur();
    public static final Dinosaur megapiranha = new MegapiranhaDinosaur();
    public static final Dinosaur baryonyx = new BaryonyxDinosaur();
    public static final Dinosaur cearadactylus = new CearadactylusDinosaur();
    public static final Dinosaur mamenchisaurus = new MamenchisaurusDinosaur();
    public static final Dinosaur chasmosaurus = new ChasmosaurusDinosaur();
    public static final Dinosaur corythosaurus = new CorythosaurusDinosaur();
    public static final Dinosaur edmontosaurus = new EdmontosaurusDinosaur();
    public static final Dinosaur lambeosaurus = new LambeosaurusDinosaur();
    public static final Dinosaur metriacanthosaurus = new MetriacanthosaurusDinosaur();
    public static final Dinosaur moganopterus = new MoganopterusDinosaur();
    public static final Dinosaur ornithomimus = new OrnithomimusDinosaur();
    public static final Dinosaur zhenyuanopterus = new ZhenyuanopterusDinosaur();
    public static final Dinosaur troodon = new TroodonDinosaur();
    public static final Dinosaur pachycephalosaurus = new PachycephalosaurusDinosaur();

    private static int entityId;

    public static List<Dinosaur> getDinosaursFromSeaLampreys()
    {
        List<Dinosaur> marineDinos = new ArrayList<Dinosaur>();

        for (Dinosaur dino : getRegisteredDinosaurs())
        {
            if (dino.isMarineAnimal() && !(dino instanceof IHybrid))
            {
                marineDinos.add(dino);
            }
        }

        return marineDinos;
    }

    public static void init()
    {
        registerDinosaurType(velociraptor);
        registerDinosaurType(achillobator);
        registerDinosaurType(anklyosaurus);
        registerDinosaurType(brachiosaurus);
        registerDinosaurType(carnotaurus);
        registerDinosaurType(coelacanth);
        registerDinosaurType(compsognathus);
        registerDinosaurType(dilophosaurus);
        registerDinosaurType(dunkleosteus);
        registerDinosaurType(gallimimus);
        registerDinosaurType(giganotosaurus);
        registerDinosaurType(indominus);
        registerDinosaurType(majungasaurus);
        registerDinosaurType(parasaurolophus);
        registerDinosaurType(pteranodon);
        registerDinosaurType(rugops);
        registerDinosaurType(segisaurus);
        registerDinosaurType(spinosaurus);
        registerDinosaurType(stegosaurus);
        registerDinosaurType(triceratops);
        registerDinosaurType(tyrannosaurus);
        registerDinosaurType(hypsilophodon);
        registerDinosaurType(dodo);
        registerDinosaurType(leptictidium);
        registerDinosaurType(microceratus);
        registerDinosaurType(oviraptor);
        registerDinosaurType(apatosaurus);
        registerDinosaurType(othnielia);
        registerDinosaurType(dimorphodon);
        registerDinosaurType(tylosaurus);
        registerDinosaurType(ludodactylus);
        registerDinosaurType(protoceratops);
        registerDinosaurType(tropeognathus);
        registerDinosaurType(leaellynasaura);
        registerDinosaurType(herrerasaurus);
        registerDinosaurType(velociraptor_blue);
        registerDinosaurType(velociraptor_charlie);
        registerDinosaurType(velociraptor_delta);
        registerDinosaurType(velociraptor_echo);
        registerDinosaurType(therizinosaurus);
        registerDinosaurType(megapiranha);
        registerDinosaurType(baryonyx);
        registerDinosaurType(cearadactylus);
        registerDinosaurType(mamenchisaurus);
        registerDinosaurType(chasmosaurus);
        registerDinosaurType(corythosaurus);
        registerDinosaurType(edmontosaurus);
        registerDinosaurType(lambeosaurus);
        registerDinosaurType(metriacanthosaurus);
        registerDinosaurType(moganopterus);
        registerDinosaurType(ornithomimus);
        registerDinosaurType(zhenyuanopterus);
        registerDinosaurType(troodon);
        registerDinosaurType(pachycephalosaurus);

        registerEntity(BluePrintEntity.class, "Blueprint");
        registerEntity(JurassiCraftSignEntity.class, "JurassiCraft Sign");
        registerEntity(CageSmallEntity.class, "Small Dinosaur Cage");
        registerEntity(PaddockSignEntity.class, "Paddock Sign");
        registerEntity(DinosaurEggEntity.class, "Dinosaur Egg");
        registerEntity(HelicopterBaseEntity.class, "Helicopter base");
        registerEntity(HelicopterSeatEntity.class, "Helicopter seat Do not spawn please, like really don't");

        dinosaurs.forEach(JCEntityRegistry::registerDinosaur);
    }

    private static void registerDinosaur(Dinosaur dinosaur)
    {
        Class<? extends DinosaurEntity> clazz = dinosaur.getDinosaurClass();

        registerEntity(clazz, dinosaur.getName());

        if (dinosaur.shouldRegister() && !(dinosaur instanceof IHybrid) && JCConfigurations.spawnJurassiCraftMobsNaturally())
        {
            if (dinosaur.isMarineAnimal())
            {
                EntityRegistry.addSpawn(clazz, 5, 1, 2, EnumCreatureType.WATER_CREATURE, BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river);
                EntitySpawnPlacementRegistry.setPlacementType(clazz, EntityLiving.SpawnPlacementType.IN_WATER);
            }
            else
            {
                EntityRegistry.addSpawn(clazz, 5, 1, 2, EnumCreatureType.CREATURE, Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class));
                EntitySpawnPlacementRegistry.setPlacementType(clazz, EntityLiving.SpawnPlacementType.ON_GROUND);
            }
        }
    }

    private static void registerEntity(Class<? extends Entity> entity, String name)
    {
        String formattedName = name.toLowerCase().replaceAll(" ", "_");

        EntityRegistry.registerModEntity(entity, formattedName, entityId++, JurassiCraft.instance, 1024, 1, true);
    }

    public static void registerDinosaurType(Dinosaur dinosaur)
    {
        dinosaur.init();

        dinosaurs.add(dinosaur);

        if (!(dinosaur instanceof IHybrid) && dinosaur.shouldRegister())
        {
            EnumTimePeriod period = dinosaur.getPeriod();

            List<Dinosaur> dinoList = dinosaursFromPeriod.get(period);

            if (dinoList != null)
            {
                dinoList.add(dinosaur);

                dinosaursFromPeriod.remove(period);
                dinosaursFromPeriod.put(period, dinoList);
            }
            else
            {
                List<Dinosaur> newDinoList = new ArrayList<Dinosaur>();
                newDinoList.add(dinosaur);

                dinosaursFromPeriod.put(period, newDinoList);
            }
        }
    }

    public static Dinosaur getDinosaurById(int id)
    {
        if (id >= dinosaurs.size() || id < 0)
        {
            return null;
        }

        return dinosaurs.get(id);
    }

    public static int getDinosaurId(Dinosaur dinosaur)
    {
        return dinosaurs.indexOf(dinosaur);
    }

    public static List<Dinosaur> getDinosaursFromAmber()
    {
        List<Dinosaur> amberDinos = new ArrayList<Dinosaur>();

        for (Dinosaur dino : getRegisteredDinosaurs())
        {
            if (!dino.isMarineAnimal() && !(dino instanceof IHybrid))
            {
                amberDinos.add(dino);
            }
        }

        return amberDinos;
    }

    public static List<Dinosaur> getDinosaurs()
    {
        return dinosaurs;
    }

    public static List<Dinosaur> getRegisteredDinosaurs()
    {
        List<Dinosaur> reg = new ArrayList<Dinosaur>();

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister())
            {
                reg.add(dino);
            }
        }

        return reg;
    }

    public static List<Dinosaur> getDinosaursFromPeriod(EnumTimePeriod period)
    {
        return dinosaursFromPeriod.get(period);
    }

    public static Dinosaur getDinosaurByClass(Class<? extends DinosaurEntity> clazz)
    {
        for (Dinosaur dino : dinosaurs)
        {
            if (dino.getDinosaurClass().equals(clazz))
            {
                return dino;
            }
        }

        return null;
    }
}
