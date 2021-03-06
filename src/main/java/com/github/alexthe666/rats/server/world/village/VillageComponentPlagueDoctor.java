package com.github.alexthe666.rats.server.world.village;

import com.github.alexthe666.rats.RatsMod;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class VillageComponentPlagueDoctor extends StructureVillagePieces.Village {
    int villagerCount = 0;
    private int averageGroundLevel = -1;

    public VillageComponentPlagueDoctor() {
        super();
    }

    public VillageComponentPlagueDoctor(StructureVillagePieces.Start startPiece, int p2, Random random, StructureBoundingBox structureBox, EnumFacing facing) {
        super();
        this.villagerCount = 0;
        this.setCoordBaseMode(facing);
        this.boundingBox = structureBox;
    }

    public static VillageComponentPlagueDoctor buildComponent(StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int x, int y, int z, EnumFacing facing, int p5) {
        if (!RatsMod.CONFIG_OPTIONS.villagePlagueDoctors) {
            return null;
        }
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 9, 8, 9, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new VillageComponentPlagueDoctor(startPiece, p5, random, structureboundingbox, facing) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {
        if (this.averageGroundLevel < 0) {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);
            if (this.averageGroundLevel < 0) {
                return false;
            }
            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 5, 0);
        }
        BlockPos blockpos = new BlockPos(this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ);
        EnumFacing facing = this.getCoordBaseMode().getOpposite();
        int xSize = this.boundingBox.maxX - this.boundingBox.minX;
        int zSize = this.boundingBox.maxZ - this.boundingBox.minZ;
        BlockPos genPos = blockpos.add(xSize/2, 0, zSize/2);
        return new WorldGenPlagueDoctor(this, facing).generate(world, random, genPos);
    }

    public IBlockState getBiomeBlock(IBlockState state) {
        return getBiomeSpecificBlockState(state);
    }
}
