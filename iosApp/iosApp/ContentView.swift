import SwiftUI



struct ContentView: View {
  
    @State private var isPickerPresented: Bool = false
    @State private var selectedAudioURL: URL?

	var body: some View {
        VStack {
           if let audioURL = selectedAudioURL {
               Text("Selected Audio: \(audioURL.lastPathComponent)")
                   .padding()
           } else {
               Button("Pick Audio") {
                   isPickerPresented.toggle()
               }
               .padding()
               .fileImporter(isPresented: $isPickerPresented, allowedContentTypes: [.audio], allowsMultipleSelection: false) { result in
                   do {
                       let selectedFile = try result.get().first!
                       selectedFile.startAccessingSecurityScopedResource()
                      
                       let data = try Data(contentsOf: selectedFile)
                       let bytes:[UInt8] = [UInt8](data)
                       print(bytes)
                       
                       selectedAudioURL = selectedFile
                   } catch {
                       print("Error picking file: \(error.localizedDescription)")
                   }
               }
           }
       }
	}
    
   
}

