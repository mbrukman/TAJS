/*
 * Copyright 2009-2019 Aarhus University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dk.brics.tajs.analysis;

import dk.brics.tajs.lattice.HostAPI;
import dk.brics.tajs.lattice.HostObject;
import dk.brics.tajs.monitoring.AnalysisMonitor;

import java.util.Objects;

import static dk.brics.tajs.analysis.HostAPIs.PARTIAL_HOST_MODEL;

/**
 * Partially modeled host APIs.
 * <p>
 * Used for being explicit about models of host APIs that are still unsound.
 * Especially useful for autogenerated models.
 * <p>
 * Current implementation features:
 * <ul>
 * <li>Partially modeled functions will cause an {@link dk.brics.tajs.util.AnalysisLimitationException.AnalysisModelLimitationException} when invoked.</li>
 * <li>Accessing definitely absent properties of Partial objects will give a special warning in {@link AnalysisMonitor}</li>
 * </ul>
 */
public class PartialHostModels implements HostObject {

    public final String identifier;

    public PartialHostModels(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartialHostModels that = (PartialHostModels) o;

        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

    @Override
    public HostAPI getAPI() {
        return PARTIAL_HOST_MODEL;
    }

    @Override
    public String toString() {
        return String.format("Partial(%s)", identifier);
    }
}
