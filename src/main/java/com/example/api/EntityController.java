package com.example.api;

import com.example.application.EntityService;
import com.example.application.TcpClientService;
import com.example.domain.Entity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class EntityController {
    private final EntityService entityService;
    private final TcpClientService tcpClientService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
        this.tcpClientService = new TcpClientService();
    }

    @GetMapping
    public List<Entity> getAllEntities() {
        return entityService.findAll();
    }

    @GetMapping("/{id}")
    public Entity getEntityById(@PathVariable Long id) {
        return entityService.findById(id);
    }

    @PostMapping
    public void createEntity(@RequestBody Entity entity) {
        entityService.save(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteEntity(@PathVariable Long id) {
        entityService.deleteById(id);
    }

    @PostMapping("/tcp-client/start")
    public void startTcpClient(@RequestParam String serverAddress) throws IOException {
        tcpClientService.startClient(serverAddress);
    }

    @PostMapping("/tcp-client/stop")
    public void stopTcpClient() throws IOException {
        tcpClientService.stopClient();
    }
}
