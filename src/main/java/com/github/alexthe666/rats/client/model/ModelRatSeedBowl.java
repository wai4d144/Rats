package com.github.alexthe666.rats.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRatSeedBowl extends ModelBase {
    public ModelRenderer block;

    public ModelRatSeedBowl() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.block = new ModelRenderer(this, 0, 0);
        this.block.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.block.addBox(-2.5F, -2.0F, -2.5F, 5, 2, 5, 0.0F);
    }

    public void render(float f5) {
        this.block.render(f5);
    }
}
