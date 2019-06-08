/*
 * $Id$
 * 
 * Copyright (c) 2016, Simsilica, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions 
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the 
 *    distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE 
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package example.view;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

import example.Main;

/**
 *
 *
 *  @author    Paul Speed
 */
public class SkyState extends BaseAppState {
    
    private BulletAppState    physicsSpace;
    private Spatial sky;
    Material mat;
    Material mat2;
    Material mat3;
    
    public SkyState() {
    }
    
    @Override
    protected void initialize( Application app ) {
        //this.physicsSpace      = this.stateManager.getState(BulletAppState.class);
        this.physicsSpace  = app.getStateManager().getState(BulletAppState.class);
        Texture texture1 = app.getAssetManager().loadTexture("Textures/galaxy+Z.jpg");
        Texture texture2 = app.getAssetManager().loadTexture("Textures/galaxy-Z.jpg"); 
        Texture texture3 = app.getAssetManager().loadTexture("Textures/galaxy+X.jpg"); 
        Texture texture4 = app.getAssetManager().loadTexture("Textures/galaxy-X.jpg"); 
        Texture texture5 = app.getAssetManager().loadTexture("Textures/galaxy+Y.jpg"); 
        Texture texture6 = app.getAssetManager().loadTexture("Textures/galaxy-Y.jpg");
 
        sky = SkyFactory.createSky(app.getAssetManager(),
                                   texture1, texture2,
                                   texture3, texture4,
                                   texture5, texture6);  
        mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
        key.setGenerateMips(true);
        Texture tex = app.getAssetManager().loadTexture(key);
        mat.setTexture("ColorMap", tex);

        mat2 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
        key2.setGenerateMips(true);
        Texture tex2 = app.getAssetManager().loadTexture(key2);
        mat2.setTexture("ColorMap", tex2);

        mat3 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key3 = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
        key3.setGenerateMips(true);
        Texture tex3 = app.getAssetManager().loadTexture(key3);
        tex3.setWrap(Texture.WrapMode.Repeat);
        mat3.setTexture("ColorMap", tex3);

        
        initFloor(); //GH no physic space yet :/ back in for testing
        
    }

    @Override
    protected void cleanup( Application app ) {
    }
    
    @Override
    protected void onEnable() {
        ((Main)getApplication()).getRootNode().attachChild(sky);
    }
    
    @Override
    protected void onDisable() {
        sky.removeFromParent();
    }
    
    public void initFloor() {
        Box floorBox = new Box(10f, 0.1f, 5f);
        floorBox.scaleTextureCoordinates(new Vector2f(3, 6));

        Geometry floor = new Geometry("floor", floorBox);
        floor.setMaterial(mat3);
        floor.setShadowMode(RenderQueue.ShadowMode.Receive);
        floor.setLocalTranslation(0, -0.1f, 0);
        floor.addControl(new RigidBodyControl(new BoxCollisionShape(new Vector3f(10f, 0.1f, 5f)), 0));
        ((Main)getApplication()).getRootNode().attachChild(floor);
        
        this.physicsSpace.getPhysicsSpace().add(floor);
    }
    
}


