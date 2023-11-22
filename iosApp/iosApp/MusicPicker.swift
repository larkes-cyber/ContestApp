import SwiftUI
import MediaPlayer
import MobileCoreServices

struct AudioPickerView: View {
    @State private var isPickerPresented: Bool = false
    @State private var selectedAudio: URL?

    var body: some View {
        VStack {
            Button("Pick Audio") {
                isPickerPresented.toggle()
            }
            .sheet(isPresented: $isPickerPresented) {
                AudioPicker(selectedAudio: $selectedAudio)
            }

            if let selectedAudio = selectedAudio {
                Text("Selected Audio: \(selectedAudio.lastPathComponent)")
            }
        }
    }
}

struct AudioPicker: UIViewControllerRepresentable {
    @Binding var selectedAudio: URL?

    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
        var parent: AudioPicker

        init(parent: AudioPicker) {
            self.parent = parent
        }

        func imagePickerController(
            _ picker: UIImagePickerController,
            didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]
        ) {
            if let audioURL = info[.mediaURL] as? URL {
                parent.selectedAudio = audioURL
            }

            picker.dismiss(animated: true)
        }

        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
            picker.dismiss(animated: true)
        }
    }

    func makeCoordinator() -> Coordinator {
        Coordinator(parent: self)
    }

    func makeUIViewController(context: Context) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.delegate = context.coordinator
        picker.sourceType = .savedPhotosAlbum
        picker.mediaTypes = [kUTTypeAudio as String]
        return picker
    }

    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {}
}
