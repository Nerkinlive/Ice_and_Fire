package com.github.alexthe666.iceandfire.client.model;

import java.util.Iterator;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelBase;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraft.entity.Entity;

import com.github.alexthe666.iceandfire.animation.AnimationBlend;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;

public abstract class ModelDragonBase extends MowzieModelBase{

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		EntityDragonBase dragon = (EntityDragonBase)entity;
		float hover = dragon.hoverProgress;
		hoverPose(hover);
	}
	
	public void progressAnimationRotation(MowzieModelRenderer modelRenderer, float sitProgress, float rotX, float rotY, float rotZ)
    {
        modelRenderer.rotateAngleX += sitProgress * rotX / 25.0F;
        modelRenderer.rotateAngleY += sitProgress * rotY / 25.0F;
        modelRenderer.rotateAngleZ += sitProgress * rotZ / 25.0F;
    }

	public void progressAnimationPos(MowzieModelRenderer modelRenderer, float sitProgress, float x, float y, float z)
    {
        modelRenderer.rotationPointX += sitProgress * x / 20.0F;
        modelRenderer.rotationPointY += sitProgress * y / 20.0F;
        modelRenderer.rotationPointZ += sitProgress * z / 20.0F;
    }
	
	public abstract void flightPose(float progress);

	public abstract void sitPose(float progress);

	public abstract void sleepPose(float progress);

	public abstract void hoverPose(float progress);

	public abstract void deadPose(float progress);

	public void normalPose() {}

}
