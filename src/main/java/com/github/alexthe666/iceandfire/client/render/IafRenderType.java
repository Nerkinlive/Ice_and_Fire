package com.github.alexthe666.iceandfire.client.render;

import com.github.alexthe666.iceandfire.client.render.tile.RenderDreadPortal;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IafRenderType extends RenderType {

    private static final ResourceLocation STONE_TEXTURE = new ResourceLocation("textures/block/stone.png");

    public IafRenderType(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
        super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
    }

    public static RenderType getDreadlandsRenderType(int iterationIn) {
        RenderState.TransparencyState renderstate$transparencystate;
        RenderState.TextureState renderstate$texturestate;
        if (iterationIn <= 1) {
            renderstate$transparencystate = TRANSLUCENT_TRANSPARENCY;
            renderstate$texturestate = new RenderState.TextureState(RenderDreadPortal.END_SKY_TEXTURE, false, false);
        } else {
            renderstate$transparencystate = ADDITIVE_TRANSPARENCY;
            renderstate$texturestate = new RenderState.TextureState(RenderDreadPortal.END_PORTAL_TEXTURE, false, false);
        }

        return makeType("dreadlands_portal", DefaultVertexFormats.POSITION_COLOR, 7, 256, false, true, RenderType.State.getBuilder().transparency(renderstate$transparencystate).texture(renderstate$texturestate).texturing(new DreadlandsPortalTexturingState(iterationIn)).build(false));
    }

    public static RenderType getStoneMobRenderType(float xSize, float ySize) {
        RenderState.TextureState textureState = new RenderState.TextureState(STONE_TEXTURE, false, false);
        RenderType.State rendertype$state = RenderType.State.getBuilder().texture(textureState).texturing(new StoneTexturingState(STONE_TEXTURE, xSize, ySize)).transparency(NO_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(true);
        return makeType("stone_entity_type", DefaultVertexFormats.ENTITY, 7, 256, rendertype$state);
    }

    public static RenderType getStoneCrackRenderType(ResourceLocation crackTex, float xSize, float ySize) {
        RenderState.TextureState renderstate$texturestate = new RenderState.TextureState(crackTex, false, false);
        RenderType.State rendertype$state = RenderType.State.getBuilder().texture(renderstate$texturestate).texturing(new StoneTexturingState(crackTex, xSize, ySize)).diffuseLighting(DIFFUSE_LIGHTING_ENABLED).alpha(DEFAULT_ALPHA).depthTest(DEPTH_EQUAL).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).overlay(OVERLAY_ENABLED).build(false);
        return makeType("stone_entity_type", DefaultVertexFormats.ENTITY, 7, 256, rendertype$state);
    }

    @OnlyIn(Dist.CLIENT)
    public static final class StoneTexturingState extends RenderState.TexturingState {
        private final float xSize;
        private final float ySize;

        public StoneTexturingState(ResourceLocation tex, float xSize, float ySize) {
            super("stone_entity_type", () -> {
                RenderSystem.matrixMode(5890);
                RenderSystem.loadIdentity();
                RenderSystem.scalef(xSize, ySize, 1);
                RenderSystem.matrixMode(5888);
            }, () -> {
                RenderSystem.matrixMode(5890);
                RenderSystem.loadIdentity();
                RenderSystem.matrixMode(5888);
            });
            this.xSize = xSize;
            this.ySize = ySize;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                StoneTexturingState renderstate$portaltexturingstate = (StoneTexturingState) p_equals_1_;
                return this.ySize == renderstate$portaltexturingstate.ySize && this.xSize == renderstate$portaltexturingstate.xSize;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Float.hashCode(this.xSize) + Float.hashCode(this.ySize);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static final class DreadlandsPortalTexturingState extends RenderState.TexturingState {
        private final int iteration;

        public DreadlandsPortalTexturingState(int p_i225986_1_) {
            super("portal_texturing", () -> {
                RenderSystem.matrixMode(5890);
                RenderSystem.pushMatrix();
                RenderSystem.loadIdentity();
                RenderSystem.translatef(0.5F, 0.5F, 0.0F);
                RenderSystem.scalef(0.5F, 0.5F, 1.0F);
                RenderSystem.translatef(17.0F / (float) p_i225986_1_, (2.0F + (float) p_i225986_1_ / 0.5F) * ((float) (Util.milliTime() % 80000L) / 80000.0F), 0.0F);
                // RenderSystem.rotatef(((float)(p_i225986_1_ * p_i225986_1_) * 4321.0F + (float)p_i225986_1_ * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
                RenderSystem.scalef(4.5F - (float) p_i225986_1_ / 4.0F, 4.5F - (float) p_i225986_1_ / 4.0F, 1.0F);
                RenderSystem.mulTextureByProjModelView();
                RenderSystem.matrixMode(5888);
                RenderSystem.setupEndPortalTexGen();
            }, () -> {
                RenderSystem.matrixMode(5890);
                RenderSystem.popMatrix();
                RenderSystem.matrixMode(5888);
                RenderSystem.clearTexGen();
            });
            this.iteration = p_i225986_1_;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                DreadlandsPortalTexturingState renderstate$portaltexturingstate = (DreadlandsPortalTexturingState) p_equals_1_;
                return this.iteration == renderstate$portaltexturingstate.iteration;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Integer.hashCode(this.iteration);
        }
    }
}
